<template>
    <div>
        <NavHeader/>
        <AdminLoginFrom/>
    </div>
</template>

<script>
    import NavHeader from "../components/NavHeader";
    import AdminLoginFrom from "../components/AdminLoginFrom";
    export default {
        name: "admin-login",
        components:{
            NavHeader,
            AdminLoginFrom
        }
    }
</script>

<style scoped>

</style>