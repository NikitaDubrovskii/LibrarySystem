<template>
  <nav>
    <v-toolbar app>
      <v-toolbar-side-icon class="grey--text" @click="drawer = !drawer"></v-toolbar-side-icon>

      <v-toolbar-title class="text-uppercase grey--text">
        <span><span class="font-weight-light">Library</span>System</span>
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-menu offset-y>
        <v-btn flat slot="activator" color="grey">
          <v-icon>expand_more</v-icon>
          <span>Menu</span>
        </v-btn>

        <v-list>
          <div v-if="profile">
            <v-list-tile v-if="profile.role === 'ADMIN'" v-for="link in linksAdmin" :key="link.text" router :to="link.route">
              <v-list-tile-title>{{ link.text }}</v-list-tile-title>
            </v-list-tile>

            <v-list-tile v-if="profile.role === null" v-for="link in linksUser" :key="link.text" router :to="link.route">
              <v-list-tile-title>{{ link.text }}</v-list-tile-title>
            </v-list-tile>
          </div>
        </v-list>
      </v-menu>

      <v-btn flat color="grey" href="/logout">
        <span>Sing Out</span>
        <v-icon right>exit_to_app</v-icon>
      </v-btn>
    </v-toolbar>

    <v-navigation-drawer app v-model="drawer" class="primary">
      <v-layout column align-center class="text-xs-center">
        <v-flex class="mt-4">
          <v-avatar size="100" color="grey lighten-2">
            <!--                        <v-img v-if="profile.picture" :src="profile.picture"></v-img>-->
          </v-avatar>

          <p class="white--text subheading mt-1">{{ profile.firstName }} {{ profile.secondName }}</p>

          <p v-if="profile.role === 'ADMIN'" class="grey--text text--lighten-2 subheading mt-1">ADMIN</p>
        </v-flex>
      </v-layout>

      <v-list class="primary">
        <div v-if="profile">
          <v-list-tile v-if="profile.role === 'ADMIN'" v-for="link in linksAdmin" :key="link.text" router :to="link.route">
            <v-list-tile-action>
              <v-icon class="white--text">{{ link.icon }}</v-icon>
            </v-list-tile-action>

            <v-list-tile-content>
              <v-list-tile-title class="white--text">{{ link.text }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>

          <v-list-tile v-if="profile.role === null" v-for="link in linksUser" :key="link.text" router :to="link.route">
            <v-list-tile-action>
              <v-icon class="white--text">{{ link.icon }}</v-icon>
            </v-list-tile-action>

            <v-list-tile-content>
              <v-list-tile-title class="white--text">{{ link.text }}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </div>
      </v-list>
    </v-navigation-drawer>

  </nav>
</template>

<script>
import { mapState } from 'vuex'

export default {
  computed: {
    ...mapState(['profile'])
  },

  data() {
    return {
      drawer: false,

      linksUser: [
        { icon: 'account_circle', text: 'Profile', route: '/profile'},
        { icon: 'library_books', text: 'Books', route: '/books'},
      ],

      linksAdmin: [
        { icon: 'account_circle', text: 'Profile', route: '/profile'},
        { icon: 'people', text: 'Users', route: '/users'},
        { icon: 'library_books', text: 'Books', route: '/books'},
      ],

    }
  },
}
</script>

<style scoped>

</style>