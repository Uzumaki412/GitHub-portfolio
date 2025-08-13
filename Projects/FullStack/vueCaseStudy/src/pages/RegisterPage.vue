<template>
    <div class="text-h4 text-center q-mt-md q-mb-md text-primary">Register</div>
    <div class="text-title2 text-center text-positive text-bold q-mt-sm">{{ state.status }}</div>
    <q-card class="q-ma-md q-pa-md">
        <q-form ref="myForm" class="q-gutter-md" greedy @submit="register" @reset="resetFields">
            <q-input outlined placeholder="First Name" id="firstName" v-model="state.firstName" :rules="[isRequired]" />
            <q-input outlined placeholder="Last Name" id="lastName" v-model="state.lastName" :rules="[isRequired]" />
            <q-input outlined placeholder="Email" id="email" v-model="state.email"
                :rules="[isRequired, isValidEmail]" />
            <q-input outlined type="password" placeholder="Password" id="password" v-model="state.password"
                :rules="[isRequired]" />
            <q-btn label="Register" type="submit" />
            <q-btn label="Reset" type="reset" />
        </q-form>
    </q-card>
</template>

<script>
import { reactive } from "vue";
import { poster } from "../utils/apiutil";

export default {
    setup() {
        let state = reactive({
            status: "",
            firstName: "",
            lastName: "",
            email: "",
            password: "",
            example: "",
        });
        const isRequired = (val) => {
            return !!val || "field is required";
        };
        const isValidEmail = (val) => {
            const emailPattern =
                /^(?=[a-zA-Z0-9@._%+-]{6,254}$)[a-zA-Z0-9._%+-]{1,64}@(?:[a-zA-Z0-9-]{1,63}\.){1,8}[a-zA-Z]{2,63}$/;
            return emailPattern.test(val) || "Invalid email";
        };
        const register = async () => {
            let customerHelper = {
                firstName: state.firstName,
                lastName: state.lastName,
                email: state.email,
                password: state.password,
            };
            try {
                let payload = await poster("customer/register", customerHelper);
                state.status = payload.token;
            } catch (err) {
                state.status = err.message;
            }
        };
        const resetFields = () => {
            state.status = "";
            state.firstName = "";
            state.lastName = "";
            state.email = "";
            state.password = "";
        };
        return {
            state,
            register,
            isRequired,
            resetFields,
            isValidEmail,
        };
    },
};
</script>