<template>
    <div>
        <NavHeader/>
        <UserRegisterFrom/>
    </div>
</template>

<script>
    import NavHeader from "../components/NavHeader";
    import UserRegisterFrom from "../components/UserRegisterFrom";
    export default {
        name: "UserRegister",
        components:{
            NavHeader,
            UserRegisterFrom
        }
    }
</script>

<style scoped>

</style>