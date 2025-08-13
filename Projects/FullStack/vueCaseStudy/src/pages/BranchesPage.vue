<template>
  <div class="text-center q-mt-md">
    <q-page class="q-pa-md">
      <div>
        <img src="/img/i-want-to.png" style="max-width:150px;" />
      </div>

      <div class="q-mt-lg">
        <h6>Find 3 closest branches to:</h6>
        <q-input class="q-ma-lg text-h5" placeholder="enter  address" id="address" v-model="state.address" />
        <q-btn label="Find 3" @click="genMap" class="q-mb-md" style="width: 30vw" />
      </div>

      <!-- <q-avatar class="q-mt-md big-avatar" size="300px" square>
      <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
    </q-avatar>
    <div class="text-h6">Find 3 closest branches to:</div>
    <div>
      <q-input class="q-ma-lg text-h5" placeholder="enter  address" id="address" v-model="state.address" />
      <br />
    </div>
    <q-btn label="Find 3" @click="genMap" class="q-mb-md" style="width: 30vw" /> -->
      <div style="height: 50vh; width: 90vw; margin-left: 5vw; border: solid" ref="mapRef"
        v-show="state.showmap === true"></div>
    </q-page>
  </div>
</template>
<script>

import { ref, reactive } from "vue";
import { fetcher } from "../utils/apiutil";


export default
  {
    name: "StoreLocator",

    setup() {
      const mapRef = ref(null);

      let state = reactive({
        status: "",
        address: "",
        showmap: false,
      })

      const getLatLon = async (address) => {
        try {
          let url = `https://api.tomtom.com/search/2/geocode/${address}.json?key=875GoTWQllatribOd3m0f6Z1huDMgs74`;
          let response = await fetch(url);
          let payload = await response.json();
          return payload.results[0].position;
        } catch (err) {
          state.status = err.message;
        }
      };

      const getClosestStores = async (lat, lon) => {
        try {
          let response = await fetcher(`branch/${lat}/${lon}`);
          return response;
        } catch (err) {
          state.status = err.message;
        }
      };

      const genMap = async () => {
        try {
          state.showmap = true;
          const tt = window.tt;
          let url = `https://api.tomtom.com/search/2/geocode/${state.address}.json?key=875GoTWQllatribOd3m0f6Z1huDMgs74`;
          let response = await fetch(url);
          let payload = await response.json();
          let lat = payload.results[0].position.lat;
          let lon = payload.results[0].position.lon;
          let map = tt.map({
            key: "875GoTWQllatribOd3m0f6Z1huDMgs74",
            container: mapRef.value,
            source: "vector/1/basic-main",
            center: [lon, lat],
            zoom: 10,
          });
          map.addControl(new tt.FullscreenControl());
          map.addControl(new tt.NavigationControl());


          let position = await getLatLon(state.address)
          console.log(position);
          let stores = await getClosestStores(position.lat, position.lon);
          console.log(stores);

          stores.forEach(store => {
            let marker = new tt.Marker()
              .setLngLat([store.longitude, store.latitude])
              .addTo(map);
            let popupOffset = 25;
            let popup = new tt.Popup({ offset: popupOffset });
            popup.setHTML(
              `<div id="popup">Store#: ${store.id}</div><div>${store.street}, ${store.city}<br/>${store.distance.toFixed(2)} mi</div>`
            );
            marker.setPopup(popup);
          });
        } catch (err) {
          state.status = err.message;
        }

      };
      return {
        mapRef,
        state,
        genMap,
        getLatLon,
        getClosestStores,
      };

    }
  };
</script>