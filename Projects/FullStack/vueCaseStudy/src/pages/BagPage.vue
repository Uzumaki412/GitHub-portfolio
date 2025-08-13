<template>
    <div class="text-center">
        <q-avatar class="q-mt-md big-avatar" size="300px" square>
            <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
        </q-avatar>
        <div class="text-h4 q-mt-md text-primary">Shopping Cart</div>
        <q-avatar class="q-mt-md" size="xl" square>
            <img :src="`img/bag.png`" style="width: 100%; height: 100%; object-fit: fill;" />
        </q-avatar>
        <div class="text-h6 text-positive">{{ state.status }}</div>

        <q-scroll-area style="height: 55vh">
            <q-card class="q-ma-md">
                <div class="row q-pa-sm bg-grey-1" style="border-bottom: 1px solid #e0e0e0;"
                    v-if="state.bag.length > 0">

                    <div class="col-4 text-weight-medium">Name</div>
                    <div class="col-2 text-center text-weight-medium">Qty</div>
                    <div class="col-3 text-right text-weight-medium">MSRP</div>
                    <div class="col-3 text-right text-weight-medium">Extended</div>
                </div>

                <div v-for="bagItem in state.bag" :key="bagItem.id" class="row q-pa-sm"
                    style="border-bottom: 1px solid #f0f0f0;">
                    <div class="col-4">{{ bagItem.item.productName }}</div>
                    <div class="col-2 text-center">{{ bagItem.qty }}</div>
                    <div class="col-3 text-right">${{ bagItem.item.msrp.toFixed(2) }}</div>
                    <div class="col-3 text-right">${{ (bagItem.item.msrp * bagItem.qty).toFixed(2) }}</div>
                </div>

                <div v-if="state.bag.length > 0" class="q-pa-md" style="background-color: #fafafa;">
                    <!-- Subtotal -->
                    <div class="row justify-end q-mb-xs">
                        <div class="col-3"></div>
                        <div class="col-3 text-right">Sub:</div>
                        <div class="col-3 text-right">${{ state.subTotal.toFixed(2) }}</div>
                    </div>

                    <!-- Tax -->
                    <div class="row justify-end q-mb-xs">
                        <div class="col-3"></div>
                        <div class="col-3 text-right">Tax:</div>
                        <div class="col-3 text-right">${{ state.tax.toFixed(2) }}</div>
                    </div>

                    <!-- Total -->
                    <div class="row justify-end" style="border-top: 1px solid #ccc; padding-top: 8px; margin-top: 8px;">
                        <div class="col-3"></div>
                        <div class="col-3 text-right text-weight-bold text-primary">Total:</div>
                        <div class="col-3 text-right text-weight-bold text-primary">${{ state.totalCost.toFixed(2) }}
                        </div>
                    </div>
                </div>
            </q-card>
            <div class="q-mt-md" v-if="state.bag.length > 0">
                <q-btn color="primary" icon="shopping_bag" label="Checkout" @click="saveBag" class="q-mr-md" />
                <q-btn color="primary" icon="delete" label="Clear Bag" @click="clearBag" />
            </div>
        </q-scroll-area>
    </div>
</template>

<style>
.img {
    max-width: 100vw;
    width: 100vw;
    height: 150px;
    overflow: hidden;
    margin: 0 auto;
    display: block;
}

.big-avatar {
    max-width: 90vw;
    width: 300px;
    height: 150px;
    margin: 0 auto;
    overflow: hidden;
}

.big-avatar img {
    width: 100%;
    height: 100%;
    object-fit: fill;
}

.q-chipD {
    background-color: #95673a;
    font-weight: bold;
}

.qtooltipD {
    background-color: #95673a;
    color: white;
    font-size: 16px;
    max-width: 80vw;
    border-radius: 8px;
    padding: 10px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
}
</style>

<script>
import { reactive, onMounted } from "vue";
import { poster } from "../utils/apiutil";


export default {
    setup() {
        let state = reactive({
            status: '',
            bag: [],
            totalCost: 0,
            subTotal: 0,
            tax: 0.13
        })

        onMounted(() => {
            if (sessionStorage.getItem('bag')) {
                state.bag = JSON.parse(sessionStorage.getItem('bag'));
            }

            if (state.bag.length > 0) {
                state.subTotal = state.bag.reduce((acc, item) => acc + (item.item.msrp * item.qty), 0);
                state.tax = state.subTotal * 0.13;
                state.totalCost = state.subTotal + state.tax;
            }
        });

        const saveBag = async () => {
            let customer = JSON.parse(sessionStorage.getItem("customer"));
            let bag = JSON.parse(sessionStorage.getItem("bag"));

            if (bag && Array.isArray(bag)) {
                bag = bag.map(item => ({
                    qtyOrdered: item.qty,
                    product: {
                        id: item.item.id,
                        brandId: item.item.brandId || 1, // Use existing brandId or default
                        productName: item.item.productName,
                        graphicName: item.item.graphicName,
                        costPrice: item.item.costPrice,
                        msrp: item.item.msrp,
                        qtyOnHand: item.item.qtyOnHand,
                        qtyOnBackOrder: item.item.qtyOnBackOrder,
                        description: item.item.description
                    }
                }));
            }

            try {
                state.status = "sending bag info to server";
                let bagHelper = { email: customer.email, selections: bag };
                let payload = await poster("Order", bagHelper);
                if (payload.indexOf("not") > 0) {
                    state.status = payload;
                } else {
                    clearBag();
                    state.status = payload;
                }
            } catch (err) {
                console.log(err);
                state.status = `Error add bag: ${err}`;
            }
        };


        const clearBag = () => {
            sessionStorage.removeItem('bag');
            state.bag = [];
            state.totalCost = 0;
            state.subTotal = 0;
            state.tax = 0.13;
            state.status = 'Bag cleared successfully!';
        };

        return {
            state,
            clearBag,
            saveBag

        };
    },
};


</script>