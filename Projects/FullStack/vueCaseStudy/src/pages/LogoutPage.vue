<template>
    <div class="text-center">
        <div class="text-h4 q-mt-lg">Customer Logout</div>
    </div>
</template>

<script>
import { onMounted } from 'vue';

export default {
    setup() {
        onMounted(() => {
            sessionStorage.removeItem("customer");

        });
    },
};
</script>