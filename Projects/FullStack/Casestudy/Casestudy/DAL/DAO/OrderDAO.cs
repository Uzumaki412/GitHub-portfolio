using Casestudy.DAL.DomainClasses;
using Casestudy.Helper;
using Casestudy.Migrations;
using Castle.Components.DictionaryAdapter.Xml;
using Microsoft.EntityFrameworkCore;
using System.Runtime.Intrinsics.X86;
using System.Transactions;

namespace Casestudy.DAL.DAO
{
    public class OrderDAO
    {

        private readonly AppDbContext _db;

        public OrderDAO(AppDbContext ctx)
        {
            _db = ctx;
        }

        public async Task<int> AddOrder(int customerId, OrderSelectionHelper[] selections)
        {
            int orderId = -1;

            using (var _trans = await _db.Database.BeginTransactionAsync())
            {
                try
                {
                    Order order = new();
                    order.CustomerId = customerId;
                    order.OrderDate = System.DateTime.Now;
                    order.OrderAmount = 0;

                    var lineItemResults = new List<(OrderSelectionHelper selection, int qtySold, int qtyBackOrdered, decimal sellingPrice)>();

                    foreach (OrderSelectionHelper selection in selections)
                    {
                        var product = await _db.Products!.FindAsync(selection.Product!.Id);
                        int qtyOrdered = selection.QtyOrdered;
                        int qtyBackOrdered, qtySold;

                        if (qtyOrdered <= product!.QtyOnHand) // if there is enough stock
                        {
                            qtySold = qtyOrdered;
                            qtyBackOrdered = 0;
                            product.QtyOnHand -= qtyOrdered; // reduce stock
                        }
                        else
                        {
                            qtySold = product.QtyOnHand; // sell all that is available
                            qtyBackOrdered = qtyOrdered - qtySold; // calculate backorder using qtySold
                            product.QtyOnBackOrder += qtyBackOrdered; // increase backorder stock
                            product.QtyOnHand = 0; // set stock to zero
                        } // else there is not enough stock


                        order.OrderAmount += product.MSRP * qtyOrdered;
                        lineItemResults.Add((selection, qtySold, qtyBackOrdered, product.MSRP));

                    }
                    await _db.Orders!.AddAsync(order);
                    await _db.SaveChangesAsync();

                    foreach (var result in lineItemResults)
                    {
                        OrderLineitem orderLineitem = new();
                        orderLineitem.OrderId = order.Id;
                        orderLineitem.ProductId = result.selection.Product!.Id;
                        orderLineitem.QtyOrdered = result.selection.QtyOrdered;
                        orderLineitem.QtySold = result.qtySold;
                        orderLineitem.QtyBackOrdered = result.qtyBackOrdered;
                        orderLineitem.SellingPrice = result.sellingPrice;

                        await _db.OrderLineitems!.AddAsync(orderLineitem);
                        await _db.SaveChangesAsync();

                    }
                    await _trans.CommitAsync();
                    orderId = order.Id;

                }
                catch (Exception e)
                {
                    Console.WriteLine(e.Message);
                    await _trans.RollbackAsync();
                }
            }
            return orderId;
        }

        public async Task<List<Order>> GetAll(int id)
        {
            return await _db.Orders!.Where(Order => Order.CustomerId == id).ToListAsync<Order>();
        }

        public async Task<List<OrderDetails>> GetOrderDetails(int oid, string email)
        {
            Customer? customer = _db.Customers!.FirstOrDefault(customer => customer.Email == email);
            List<OrderDetails> allDetails = new();
            var results = from o in _db.Orders
                          join ol in _db.OrderLineitems! on o.Id equals ol.OrderId
                          join p in _db.Products! on ol.ProductId equals p.Id
                          where (o.CustomerId == customer!.Id && o.Id == oid)
                          select new OrderDetails
                          {
                              OrderId = o.Id,
                              CustomerId = customer!.Id,
                              QtyOrdered = ol.QtyOrdered,
                              Description = p.Description,
                              ProductName = p.ProductName,
                              GraphicName = p.GraphicName,
                              CostPrice = p.CostPrice,
                              MSRP = p.MSRP,
                              QtyOnHand = p.QtyOnHand,
                              QtyOnBackOrder = p.QtyOnBackOrder,
                              QtySold = ol.QtySold,
                              OrderDate = o.OrderDate.ToString("yyyy/MM/dd - hh:mm tt")

                          };
            allDetails = await results.ToListAsync();
            return allDetails;
        }



    }
}
