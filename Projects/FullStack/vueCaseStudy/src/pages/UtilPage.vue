<template>
  <div class="text-center">
    <q-avatar class="img q-mb-md" size="x1" square>
      <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
    </q-avatar>
    <div class="text-h2 q-mt-lg">Utility</div>
    <!-- This button will call the loadTables function -->
    <q-btn class="q-ma-sm" color="white" text-color="black" label="Load Tables" @click="loadTables" />

    <!--Tell the user if the fetch api call worked or not
  This div will show the status of the loadTables function-->
    <div class="status q-mt-md text-subtitle2 text-negative" text-color="red">
      {{ state.status }}
    </div>
  </div><!-- to put centered -->



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
import { reactive } from "vue";
import { fetcher } from "../utils/apiutil";

export default {
  setup() {
    let state = reactive
      ({
        status: "",
      });

    //loadTables function
    // This function will call the ASP.Net core server to load the tables
    const loadTables = async () => {
      try {
        state.status = "resetting exercise tables ...";
        state.status = await fetcher(`Data`);
      } catch (err) {
        console.log(err);
        state.status = `Error has occured: ${err.message}`;
      }
    };



    // return the state and loadTables function
    // to be used in the template
    return {
      loadTables,
      state,
    };
  },
};
</script>