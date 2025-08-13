<template>
  <div class="text-center">
    <q-avatar class="img q-mb-md" size="x1" square>
      <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
    </q-avatar>
    <div class=" text-h2 q-mt-lg">Brand
    </div>
    <!-- This button will call the loadTables function -->


    <!--Tell the user if the fetch api call worked or not
  This div will show the status of the loadTables function-->
    <div class="status q-mt-md text-subtitle2 text-negative" text-color="red">
      {{ state.status }}
    </div>

    <!-- This div will show the status of the loadCategory function-->
    <q-select class="q-mt-lg q-ml-lg" v-if="state.brands.length > 0"
      style="width: 50vw; margin-bottom: 4vh; background-color: #fff" :option-value="'id'" :option-label="'name'"
      :options="state.brands" label="Select a Brand" v-model="state.selectedBrandId" @update:model-value="getProduct()"
      emit-value :map-options="true" />

    <div class="text-h6 text-bold text-center text-primary" v-if="state.product.length > 0">
      {{ state.selectedBrand.name }} ITEMS
    </div>
    <q-scroll-area style="height: 55vh">
      <q-card class="q-ma-md">
        <q-list separator>
          <q-item clickable v-for="item in state.product" :key="item.id" @click="selectMenuItem(item.id)">
            <q-item-section avatar>
              <q-avatar style="height: 125px; width: 90px" square>
                <img :src="`/img/${item.graphicName}`" />
              </q-avatar>
            </q-item-section>
            <q-item-section class="text-left">
              {{ item.productName }}

            </q-item-section>
          </q-item>
        </q-list>
      </q-card>
    </q-scroll-area>

    <q-dialog v-model="state.itemSelected" transition-show="rotate" transition-hide="rotate">
      <q-card>
        <q-card-actions align="right">
          <q-btn flat label="X" color="primary" v-close-popup class="text-h5" />
        </q-card-actions>
        <q-card-section>
          <div class="text-subtitle2 text-center">
            {{ state.selectedMenuItem.productName }} - {{ formatCurrency(state.selectedMenuItem.msrp) }}
          </div>
        </q-card-section>
        <q-card-section class="text-center">
          <q-img :src="`/img/${state.selectedMenuItem.graphicName}`" />
        </q-card-section>
        <q-card-section>

          <q-chip icon="bookmark" class="q-chipD" text-color="white">Description
            <q-tooltip class="qtooltipD" transition-show="flip-right" transition-hide="flip-left" text-color="white">
              <div>
                <div class="infoBar">
                  <p style="font-weight: bold;"><span>{{ state.selectedMenuItem.description }}</span></p>
                </div>
              </div>
            </q-tooltip>
          </q-chip>
        </q-card-section>

        <q-card-section>
          <div class="row">
            <q-input v-model.number="state.qty" type="number" filled placeholder="qty" hint="Qty" dense
              style="max-width:12vw;" />
            <q-btn class="q-chipD" text-color="white" label="Add To Bag" :disable="state.qty < 0" @click="addToBag()"
              style="max-width:25vw;margin-left:3vw" />
            <q-btn class="q-chipD" text-color="white" label="View To Bag" @click="viewBag()"
              style="max-width:25vw;margin-left:3vw" />
          </div>
        </q-card-section>

        <q-card-section class="text-center text-positive">
          {{ state.dialogStatus }}
        </q-card-section>
      </q-card>
    </q-dialog>

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
import { formatCurrency } from "../utils/formatutils";
import { useRouter } from "vue-router";

export default {
  setup() {
    let state = reactive
      ({
        status: "",
        brands: [],
        product: [],
        selectedBrandId: "",
        selectedBrand: {},
        selectedMenuItem: {},
        dialogStatus: "",
        itemSelected: false,
        qty: 0,
        bag: [],
      });

    onMounted(() => {
      loadBrands();
    });

    const loadBrands = async () => {
      try {
        state.status = "Brands Loaded";
        state.brands = await fetcher(`Brand`);
      } catch (err) {
        console.log(err);
        state.status = `Error has occured: ${err.message}`;
      }
    };

    const getProduct = async () => {
      try {
        state.selectedBrand = state.brands.find(
          (brands) => brands.id === state.selectedBrandId
        );
        state.status = `finding product for category ${state.selectedBrand}...`;
        state.product = await fetcher(
          `Product/${state.selectedBrand.id}`
        );
        state.status = `loaded ${state.product.length} products for
                ${state.selectedBrand.name}`;
      } catch (err) {
        console.log(err);
        state.status = `Error has occured: ${err.message}`;
      }
    };

    const selectMenuItem = async (menuitemid) => {
      try {
        // q-item click sends us the id, so we need to find the object
        state.selectedMenuItem = state.product.find(
          (item) => item.id === menuitemid
        );
        state.itemSelected = true;
        state.dialogStatus = "";
        if (sessionStorage.getItem("bag")) {
          state.bag = JSON.parse(sessionStorage.getItem("bag"));
        }
      } catch (err) {
        console.log(err);
        state.status = `Error has occured: ${err.message}`;
      }
    }

    // addToBag function to add the selected menu item to the bag
    const addToBag = () => {
      let index = -1;
      if (state.bag.length > 0) {
        index = state.bag.findIndex(
          // is item already on the bag
          (item) => item.id === state.selectedMenuItem.id
        );
      }
      if (state.qty > 0) {
        index === -1 // add
          ? state.bag.push({
            id: state.selectedMenuItem.id, qty: state.qty, item:
              state.selectedMenuItem,
          })
          : (state.bag[index] = {
            // replace
            id: state.selectedMenuItem.id,
            qty: state.qty,
            item: state.selectedMenuItem,
          });
        state.dialogStatus = `${state.qty} item(s) added`;
      } else {
        index === -1 ? null : state.bag.splice(index, 1); // remove
        state.dialogStatus = `item(s) removed`;
      }
      sessionStorage.setItem("bag", JSON.stringify(state.bag));
      state.qty = 0;
    };

    const router = useRouter();

    const viewBag = () => {
      router.push("bag");
    };


    // return the state and loadTables function
    // to be used in the template
    return {
      loadBrands,
      state,
      getProduct,
      selectMenuItem,
      addToBag,
      formatCurrency,
      viewBag,
    };
  },
};
</script>