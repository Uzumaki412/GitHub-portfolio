const routes = [
  {
    path: "/",
    component: () => import("layouts/MainLayout.vue"),
    children: [
      // Home Page
      {
        path: "/",
        name: "home",
        component: () => import("pages/HomePage.vue"),
      },

      // About Page
      {
        path: "/about",
        name: "about",
        component: () => import("pages/AboutPage.vue"),
      },

      // UntilPage
      {
        path: "/util",
        name: "util",
        component: () => import("pages/UtilPage.vue"),
      },

      // brand Page
      {
        path: "/brand",
        name: "brand",
        component: () => import("pages/BrandListPage.vue"),
      },
      // bag Page
      {
        path: "/bag",
        name: "bag",
        component: () => import("pages/BagPage.vue"),
      },
      // history Page
      {
        path: "/history",
        name: "history",
        component: () => import("pages/orderHistory.vue"),
      },

      // Branches Page
      {
        path: "/branches",
        name: "branches",
        component: () => import("pages/BranchesPage.vue"),
      },

      {
        path: "/register",
        name: "register",
        component: () => import("pages/RegisterPage.vue"),
      },
      {
        path: "/login",
        name: "login",
        component: () => import("pages/LoginPage.vue"),
      },
      {
        path: "/logout",
        name: "logout",
        component: () => import("pages/LogoutPage.vue"),
      }

    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: () => import("pages/ErrorNotFound.vue"),
  },
];
export default routes;
