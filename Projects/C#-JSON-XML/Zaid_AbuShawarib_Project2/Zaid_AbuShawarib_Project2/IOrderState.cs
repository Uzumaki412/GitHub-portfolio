using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zaid_AbuShawarib_project2
{
    public interface IOrderState
    {
        void Handle(CoffeeShop shop);
    }
}
