<template>
  <v-dialog max-width="400px">
    <v-btn slot="activator" small icon>
      <v-icon>info</v-icon>
    </v-btn>
    <v-card class="text-xs">
      <v-card-title>
        <h2>{{ user.firstName }} {{ user.secondName }}</h2>
      </v-card-title>
      <v-card-text>
        <div> <span class="font-weight-bold">Имя:</span> {{ user.firstName }}</div>
        <div> <span class="font-weight-bold">Фамилия:</span> {{ user.secondName }}</div>
        <div> <span class="font-weight-bold">Возраст:</span> {{ user.age }}</div>
        <div> <span class="font-weight-bold">Дата создания:</span> {{ user.creation_date }}</div>
        <div> <span class="font-weight-bold">Почта:</span> {{ user.email }}</div>
        <div> <span class="font-weight-bold">Телефон:</span> {{ user.phone }}</div>
        <br/>
        <div class="font-weight-bold"> Книги:</div>
        <div v-for="book in user.books" :key="book.id">
          <div>{{ book.title }}, {{ book.author }}</div>
        </div>
      </v-card-text>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  props: ['user']
}
</script>

<style scoped>

</style>