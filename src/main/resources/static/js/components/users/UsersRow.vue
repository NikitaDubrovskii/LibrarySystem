<template>

  <!--<div id="e3" style="max-width: 500px;" >

      <v-toolbar color="pink" dark>
          <v-toolbar-title>
              <i>({{ user.id }})</i> {{ user.firstName }} {{ user.secondName }}, {{ user.age }} год
          </v-toolbar-title>

          <v-spacer></v-spacer>

          <v-card-actions>
              <v-btn @click="edit" small >
                  Edit
              </v-btn>
              <v-btn @click="del" small >
                  <v-icon>delete</v-icon>
              </v-btn>
          </v-card-actions>

      </v-toolbar>

      <v-card class="mb-3">

          <v-container fluid grid-list-lg>
              <v-layout row wrap>
                  <v-flex xs12>
                      <v-card color="blue-grey darken-2" class="white&#45;&#45;text">
                          <v-card-title primary-title>
                              <div>
                                  <div class="headline">Unlimited music now</div>
                                  <span>Listen to your favorite artists and albums whenever and wherever, online and offline.</span>
                              </div>
                          </v-card-title>
                          <v-card-actions>
                              <v-btn flat dark>Listen now</v-btn>
                          </v-card-actions>
                      </v-card>
                  </v-flex>
              </v-layout>
          </v-container>

      </v-card>

  </div>-->


  <div>

    <v-card flat class="pa-3">
      <v-layout row wrap>
        <v-flex xs1 sm1 md1>
          <div class="caption grey--text">Id</div>
          <div>{{ user.id }}</div>
        </v-flex>

        <v-flex xs6 sm3 md3>
          <div class="caption grey--text">Name</div>
          <div>{{ user.firstName }} {{ user.secondName }}</div>
        </v-flex>

        <v-flex xs1 sm1 md1>
          <div class="caption grey--text">Age</div>
          <div>{{ user.age }}</div>
        </v-flex>

        <v-flex xs8 sm6 md3>
          <div class="caption grey--text">Email</div>
          <div>{{ user.email }}</div>
        </v-flex>

        <v-flex xs8 md2>
          <div class="caption grey--text">Phone number</div>
          <div>{{ user.phone }}</div>
        </v-flex>

        <v-spacer></v-spacer>

        <v-flex xs12 sm12 md2>
          <div class="right">
            <v-btn small icon @click="show = !show">
              <v-icon>{{ show ? 'keyboard_arrow_up' : 'keyboard_arrow_down' }}</v-icon>
            </v-btn>
            <v-btn small icon @click="del">
              <v-icon>delete</v-icon>
            </v-btn>
            <users-edit-form :user="user" :userAttr="editUser" @click="edit"/>
<!--            <v-btn small icon @click="edit">
              <v-icon>edit</v-icon>
            </v-btn>-->
          </div>
        </v-flex>

        <v-slide-y-transition>
          <v-card-text v-show="show">
            <div v-if="user.books.length > 0">
              <div v-for="book in user.books" :key="book.id">
                <div>{{ book.title }} --- {{ book.author }}</div>
              </div>
            </div>
            <div v-else>Книг нет</div>
          </v-card-text>
        </v-slide-y-transition>

        <!--  <v-flex xs12 sm12 md12>
              <v-btn small icon @click="del" flat fab>
                  <v-icon>delete</v-icon>
              </v-btn>
          </v-flex>-->
      </v-layout>
    </v-card>

  </div>

</template>

<script>
import { mapActions} from 'vuex'
import UsersEditForm from "./UsersEditForm.vue";

export default {
  components: { UsersEditForm },

  props: ['user', 'editUser'],

  methods: {
    ...mapActions(['removeUserAction']),

    edit() {
      this.editUser(this.user)
    },

    del() {
      this.removeUserAction(this.user)
    }
  },

  data: () => ({
    show: false
  })
}
</script>

<style scoped>

</style>