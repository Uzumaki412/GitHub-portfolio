<template>
  <div class="text-center">
    <q-avatar class="img q-mb-md" size="x1" square>
      <img :src="`/img/i-want-to.png`" style="width: 100%; height: 100%; object-fit: fill;" />
    </q-avatar>
    <div class="text-h2 q-mt-lg">Home</div>
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