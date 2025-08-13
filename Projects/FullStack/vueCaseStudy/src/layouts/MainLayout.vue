<template>
  <q-layout view="lHh Lpr lFf">
    <q-footer elevated class="bg-grey-8 text-white">
      <q-toolbar>
        <div class="text-subtitle2">best viewed on mobile device — Zaid Abu Shawarib &copy;{{ new Date().getFullYear()
        }}
        </div>
      </q-toolbar>
    </q-footer>
    <q-header elevated>
      <q-toolbar>
        <q-toolbar-title> Info3181 Casestudy Application </q-toolbar-title>
        <q-btn flat round dense icon="reorder" class="q-mr-xs">
          <q-menu>
            <q-list style="min-width: 100px">
              <q-item clickable v-close-popup to="/" v-if="isLoggedIn()">
                <q-item-section>Home</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="util" v-if="isLoggedIn()">
                <q-item-section>Util</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="brand" v-if="isLoggedIn()">
                <q-item-section>Brands</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="branches" v-if="isLoggedIn()">
                <q-item-section>Branches</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="bag" v-if="isLoggedIn()">
                <q-item-section>Bag</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="history" v-if="isLoggedIn()">
                <q-item-section>Order History</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="register" v-if="!isLoggedIn()">
                <q-item-section>Register</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="login" v-if="!isLoggedIn()">
                <q-item-section>Login</q-item-section>
              </q-item>
              <q-item clickable v-close-popup to="logout" v-if="isLoggedIn()">
                <q-item-section>Logout</q-item-section>
              </q-item>
            </q-list>
          </q-menu>
        </q-btn>
      </q-toolbar>
    </q-header>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { defineComponent } from "vue";
export default defineComponent({
  name: "MainLayout",

  setup() {
    const isLoggedIn = () => {
      if (sessionStorage.getItem("customer")) {
        return true;
      }
      return false;
    };
    return {
      isLoggedIn
    };
  },
});
</script>