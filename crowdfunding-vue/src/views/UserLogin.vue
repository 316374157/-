<template>
    <div>
        <NavHeader/>
        <UserLoginFrom/>
    </div>
</template>

<script>
    import NavHeader from "../components/NavHeader";
    import UserLoginFrom from "../components/UserLoginFrom";
    export default {
        name: "UserLogin",
        components:{
            NavHeader,
            UserLoginFrom
        }
    }
</script>

<style scoped>

</style>