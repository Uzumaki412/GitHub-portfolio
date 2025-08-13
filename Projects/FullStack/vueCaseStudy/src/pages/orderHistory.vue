<template>
    <div class="text-center">
        <q-avatar class="q-mt-md big-avatar" size="300px" square>
            <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
        </q-avatar>
        <div class="text-h4 q-mt-md text-primary">Order History</div>
        <div class="text-h6 text-positive">{{ state.status }}</div>

        <q-scroll-area style="height: 55vh">
            <q-card class="q-ma-md">
                <div class="row q-pa-sm bg-grey-1" style="border-bottom: 1px solid #e0e0e0;"
                    v-if="state.order.length > 0">

                    <div class="col-4 text-weight-medium">#</div>
                    <div class="col-4 text-weight-medium">Date</div>

                </div>

                <div v-for="order in state.order" :key="order.id" class="row q-pa-sm"
                    style="border-bottom: 1px solid #f0f0f0;" @click="selectedOrder(order.id)">
                    <div class="col-4">{{ order.id }}</div>
                    <div class="col-4">{{ formatDate(order.orderDate) }}</div>
                </div>
            </q-card>
        </q-scroll-area>
    </div>

    <q-dialog v-model="state.showOrderDialog" transition-show="rotate" transition-hide="rotate">
        <q-card style="min-width: 70vw; max-width: 90vw; min-height: 60vh;">
            <q-card-actions align="right">
                <q-btn flat label="X" color="primary" v-close-popup class="text-h5" />
            </q-card-actions>

            <q-card-section class="text-center">
                <div class="text-h5 text-center text-primary">
                    Tray #{{ state.selectedOrderId }}
                </div>
                <div class="text-subtitle2 text-center text-grey-6">
                    {{ formatDate(state.orderDate) }}
                </div>
                <q-avatar class="q-mt-md big-avatar" size="300px" square>
                    <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
                </q-avatar>
            </q-card-section>
            <q-card-section>
                <q-scroll-area style="height: 30vh">

                    <!-- <div class="row q-pa-sm bg-grey-2" style="border-bottom: 2px solid #1976d2;">
                        <div class="col-3 text-weight-bold">Name</div>

                        <div class="col-6 text-center">
                            <div class="text-weight-bold">Quantities</div>
                            <div class="row q-mt-xs">
                                <div class="col text-center">S</div>
                                <div class="col text-center">O</div>
                                <div class="col text-center">B</div>
                            </div>
                        </div>

                        <div class="col-3 text-right text-weight-bold">Extended</div>
                    </div> -->

                    <div class="row q-pa-sm bg-grey-2" style="border-bottom: 2px solid #1976d2;">
                        <div class="col-3 text-weight-bold">Name</div>

                        <div class="col-6 text-center">
                            <div class="text-weight-bold">Quantities</div>
                            <div class="row q-mt-xs no-wrap">
                                <div class="col-4 text-center">S</div>
                                <div class="col-4 text-center">O</div>
                                <div class="col-4 text-center">B</div>
                            </div>
                        </div>

                        <div class="col-3 text-right text-weight-bold">Extended</div>
                    </div>

                    <!-- <div v-for="order in state.orderDetails" :key="order.id" class="row q-pa-sm"
                        style="border-bottom: 1px solid #e0e0e0;">
                        <div class="col-2">{{ order.productName }}</div>
                        <div class="col-2 text-center">{{ order.qtySold }}</div>
                        <div class="col-2 text-center">{{ order.qtyOrdered }}</div>
                        <div class="col-2 text-center">{{ order.qtyOnBackOrder }}</div>
                        <div class="col-3 text-right">${{ (order.msrp * order.qtySold).toFixed(2) }}</div>
                    </div> -->

                    <div v-for="order in state.orderDetails" :key="order.id" class="row q-pa-sm"
                        style="border-bottom: 1px solid #e0e0e0;">
                        <div class="col-3">{{ order.productName }}</div>

                        <div class="col-6">
                            <div class="row no-wrap">
                                <div class="col-4 text-center">{{ order.qtySold }}</div>
                                <div class="col-4 text-center">{{ order.qtyOrdered }}</div>
                                <div class="col-4 text-center">{{ order.qtyOnBackOrder }}</div>
                            </div>
                        </div>

                        <div class="col-3 text-right">${{ (order.msrp * order.qtySold).toFixed(2) }}</div>
                    </div>

                    <div v-if="state.orderDetails.length > 0" class="q-pa-md" style="background-color: #fafafa;">
                        <!-- Subtotal -->
                        <div class="row justify-end q-mb-xs">
                            <div class="col-6"></div>
                            <div class="col-3 text-right text-weight-medium">Sub:</div>
                            <div class="col-3 text-right">${{ calculateSubtotal().toFixed(2) }}</div>
                        </div>

                        <!-- Tax -->
                        <div class="row justify-end q-mb-xs">
                            <div class="col-6"></div>
                            <div class="col-3 text-right text-weight-medium">Tax(13%):</div>
                            <div class="col-3 text-right">${{ calculateTax().toFixed(2) }}</div>
                        </div>

                        <!-- Total -->
                        <div class="row justify-end">
                            <div class="col-6"></div>
                            <div class="col-3 text-right text-weight-bold text-primary">Total:</div>
                            <div class="col-3 text-right text-weight-bold text-primary">${{ calculateTotal().toFixed(2)
                            }}</div>
                        </div>
                    </div>


                </q-scroll-area>
            </q-card-section>
        </q-card>
    </q-dialog>


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
import { fetcher } from "../utils/apiutil";
import { formatDate } from "../utils/formatutils";


export default {
    setup() {
        let state = reactive({
            status: '',
            order: [],
            orderDetails: [],
            showOrderDialog: false,
            selectedOrderId: null,
        })

        const loadOrders = async () => {
            let customer = JSON.parse(sessionStorage.getItem("customer"));
            try {
                state.status = `Loading orders...`;
                let payload = await fetcher(`order/` + customer.email);
                state.order = payload;
                state.status = `${payload.length} Order(s) loaded successfully`;
            } catch (error) {
                state.status = `Error loading orders: ${error.message}`;
            }
        }

        const selectedOrder = async (orderId) => {
            let customer = JSON.parse(sessionStorage.getItem("customer"));
            try {
                state.status = `Loading order ${orderId} details...`;
                let payload = await fetcher(`order/${orderId}/${customer.email}`);
                state.orderDetails = payload;
                console.log(state.orderDetails);
                state.showOrderDialog = true;
                state.selectedOrderId = orderId;
                state.status = `Order ${orderId} details loaded successfully`;
            } catch (err) {
                console.log(err);
                state.status = `Error loading order details: ${err.message}`;
            }
        }

        onMounted(loadOrders)


        const calculateSubtotal = () => {
            return state.orderDetails.reduce((sum, item) => {
                // Use the calculation from your template
                const extended = (item.msrp || 0) * (item.qtySold || 0);
                return sum + extended;
            }, 0);
        };

        const calculateTax = () => {
            return calculateSubtotal() * 0.13;
        };

        const calculateTotal = () => {
            return calculateSubtotal() + calculateTax();
        };
        return {
            state,
            formatDate,
            selectedOrder,
            calculateSubtotal,
            calculateTax,
            calculateTotal
        };
    },
};


</script>