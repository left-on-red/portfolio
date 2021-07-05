import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import Collection from '../views/Collection.vue';
import Pokemon from '../views/Pokemon.vue';
import Shiny from '../views/Shiny.vue';

Vue.use(VueRouter);

let routes = [
    { path: '/', component: Home },
    { path: '/collection', component: Collection },
    { path: '/pokemon/:id', component: Pokemon },
    {
        path: '/shiny/:id',
        component: Shiny

        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        //component: () => import(/* webpackChunkName: "about" */ '../views/Shiny.vue')
    }
]

const router = new VueRouter({ routes });

export default router
