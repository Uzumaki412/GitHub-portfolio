using System.ComponentModel.DataAnnotations.Schema;
using System.ComponentModel.DataAnnotations;

namespace Casestudy.DAL.DomainClasses
{
    public class Product
    {
        [Key]
        public string? Id { get; set; }
        [ForeignKey("BrandId")]
        public Brand? Brand { get; set; }
        public string? ProductName { get; set; }
        public string? GraphicName { get; set; }
        [Column(TypeName = "money")]
        public decimal CostPrice { get; set; }
        [Column(TypeName = "money")]
        public decimal MSRP { get; set; }
        public int QtyOnHand { get; set; }
        public int QtyOnBackOrder { get; set; }
        [StringLength(200)]
        public string? Description { get; set; }
    }
}
