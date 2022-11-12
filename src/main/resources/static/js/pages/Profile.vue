<template>
    <!--<v-container>
        <v-layout justify-space-around>
            <v-flex :xs6="!$vuetify.breakpoint.xsOnly">
                <div class="title mb-3">
                    User Profile
                </div>
                <v-layout row justify-space-between>
                    <v-flex class="px-1">
                        <v-img :src="profile.picture"></v-img>
                    </v-flex>

                    <v-flex class="px-1">
                        <v-layout column>
                            <v-flex>Имя: {{profile.firstName}} {{profile.secondName}}</v-flex>
                            <v-flex>Возраст: {{profile.age}}</v-flex>
                            <v-flex>Почта: {{profile.email}}</v-flex>
                            <v-flex>Страна: {{profile.locale}}</v-flex>
                            <v-flex>Дата создарния: {{profile.creationDate}}</v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>-->

    <v-container class="my-5">
        <v-layout align-center justify-center row fill-height>
            <v-flex xs8 sm6 md4>
                <v-card class="text-xs-center pa-1">
                    <v-responsive class="pt-4">
                        <v-avatar size="100" color="grey lighten-2">
<!--                            <v-img v-if="profile.picture" :src="profile.picture"></v-img>-->
                        </v-avatar>
                    </v-responsive>

                    <v-card-text>
                        <div class="subheading">{{profile.firstName}} {{profile.secondName}}</div>
                        <div class="grey--text">{{profile.age}}</div>
                        <div class="grey--text">{{profile.email}}</div>
                        <div class="grey--text">{{profile.locale}}</div>
                        <div class="grey--text">{{profile.creationDate}}</div>
                    </v-card-text>

                    <v-card-actions>
                        <v-btn flat color="grey">
                            <v-icon small left>edit</v-icon>
                            <span>Edit</span>
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'

    export default {
        computed: {
            ...mapState(['profile']),
        },
    }
</script>

<style scoped>

</style>