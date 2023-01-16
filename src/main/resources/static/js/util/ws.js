import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

let stompClient = null;
const handlers = []

export function connect() {
    const socket = new SockJS('/gs-guide-websocket')
    stompClient = Stomp.over(socket)
    stompClient.debug = () => {}
    stompClient.connect({}, frame => {
        stompClient.subscribe('/topic/activity', item => {
            handlers.forEach(handler => handler(JSON.parse(item.body)))
        })
    })
}

export function addHandlers(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

export function sendItem(item) {
    stompClient.send("/app/changeItem", {}, JSON.stringify(item))
}

/*
export function sendBook(book) {
    stompClient.send("/app/changeBook", {}, JSON.stringify(book))
}*/
