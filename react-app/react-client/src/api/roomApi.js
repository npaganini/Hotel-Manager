import { post, get } from "./baseApi";

const baseRoomURL = "/rooms";

const getAllBusyRoomsUrl = baseRoomURL + "";
const getFreeRoomsUrl = baseRoomURL + "/free";
const getUndeliveredOrdersUrl = baseRoomURL + `/orders`;
const postReservationUrl = baseRoomURL + "/reservation";
const reservationsUrl = baseRoomURL + "/reservations";
const postCheckingUrl = id => baseRoomURL + `/checkin/${id}`;
const postCheckoutUrl = id => baseRoomURL + `/checkout/${id}`;
const postSendOrdersToRoomUrl = id => baseRoomURL + `/orders/${id}`;
const postRegisterOccupantsUrl = id => baseRoomURL + `/occupants/${id}`;

export const getAllBusyRooms = async (params) => get(getAllBusyRoomsUrl, params);
export const getFreeRooms = async (params) => get(getFreeRoomsUrl, params);
export const getAllUndeliveredOrders = async (params) => get(getUndeliveredOrdersUrl, params);
export const getAllReservations = async (params) => get(reservationsUrl, params);
export const doReservation = async (body) => post(postReservationUrl, body);
export const doCheckin = async (id) => post(postCheckingUrl(id));
export const doCheckout = async (body, id) => post(postCheckoutUrl(id), body);
export const sendOrderToRoom = async (id) => post(postSendOrdersToRoomUrl(id));
export const registerOccupants = async (body, id) => post(postRegisterOccupantsUrl(id), body);

