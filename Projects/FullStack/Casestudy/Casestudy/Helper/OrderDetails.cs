using System.ComponentModel.DataAnnotations.Schema;

namespace Casestudy.Helper
{
    public class OrderDetails
    {
        public int OrderId { get; set; }
        public int ProductId { get; set; }
        public int QtyOrdered { get; set; }
        public string? Description { get; set; }
        public int CustomerId { get; set; }
        public string? OrderDate { get; set; }
        public string? ProductName { get; set; }
        public string? GraphicName { get; set; }
        [Column(TypeName = "money")]
        public decimal CostPrice { get; set; }
        [Column(TypeName = "money")]
        public decimal MSRP { get; set; }
        public int QtyOnHand { get; set; }
        public int QtyOnBackOrder { get; set; }

        public int QtySold
        {
            get
            {
                return QtyOrdered - QtyOnBackOrder;
            }

            set
            {
                QtyOrdered = value + QtyOnBackOrder;
            }
        }
    }
}
