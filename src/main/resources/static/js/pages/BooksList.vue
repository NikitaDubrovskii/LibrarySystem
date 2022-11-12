<template>
  <!--<v-container>
      <v-flex xs6>
          <v-layout align-start justify-start column fill-height>
              <books-row v-for="book in sortedBooks"
                         :key="book.id"
                         :book="book" />
          </v-layout>
      </v-flex>
  </v-container>-->

  <div>
    <h1 class="subheading grey--text">Books</h1>
    <books-form class="right"/>

    <v-container class="my-5">
      <books-row v-for="book in sortedBooks"
                 :key="book.id"
                 :book="book"
                 :editBook="editBook"
                 class="mb-1 elevation-1"/>
    </v-container>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import BooksRow from "components/books/BooksRow.vue"
import BooksForm from "components/books/BooksForm.vue"

export default {
  components: {
    BooksRow,
    BooksForm
  },

  data() {
    return {
      book: null
    }
  },

  computed: {
    ...mapGetters(['sortedBooks']),
  },

  methods: {
    editBook(book) {
      this.book = book
    }
  }
}
</script>

<style scoped>

</style>