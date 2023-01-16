<template>
 <div>
    <v-card flat class="pa-3">
      <v-layout row wrap>
        <v-flex xs1 sm1 md1>
          <div class="caption grey--text">Id</div>
          <div>{{ book.id }}</div>
        </v-flex>

        <v-flex xs6 md4>
          <div class="caption grey--text">Title</div>
          <div>{{ book.title }}</div>
        </v-flex>

        <v-flex xs6 sm4 md2>
          <div class="caption grey--text">Author</div>
          <div>{{ book.author }}</div>
        </v-flex>

        <v-flex xs3 sm3 md1>
          <div class="caption grey--text">Year</div>
          <div>{{ book.year }}</div>
        </v-flex>

        <v-flex xs3 sm3 md1>
          <div class="caption grey--text">Pages</div>
          <div>{{ book.pages }}</div>
        </v-flex>

        <v-spacer/>

        <v-flex v-if="profile.role === 'ADMIN'" xs12 sm12 md2>
          <div class="right">
            <v-btn small icon @click="del">
              <v-icon>delete</v-icon>
            </v-btn>
            <books-edit-form :book="book" :bookAttr="editBook" @click="edit"/>
<!--            <v-btn small icon @click="edit" :book="book" :bookAttr="editBook">
              <v-icon>edit</v-icon>
            </v-btn>-->
          </div>
        </v-flex>

      </v-layout>
    </v-card>

  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import BooksEditForm from "./BooksEditForm.vue";

export default {
  computed: {
    ...mapState(['profile'])
  },

  components: { BooksEditForm },

  props: ['book', 'editBook'],

  methods: {
    ...mapActions(['removeBookAction']),

    edit() {
      this.editBook(this.book)
    },

    del() {
      this.removeBookAction(this.book)
    }
  }
}
</script>

<style scoped>

</style>