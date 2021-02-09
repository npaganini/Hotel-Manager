import { post, get } from "./baseApi";

const baseRoomURL = "/rooms";

const getAllRomsUrl = baseRoomURL + "";
const getFreeRoomsUrl = baseRoomURL + "/free";
const postReservationUrl = baseRoomURL + "/reservation";
const postCheckingUrl = id => baseRoomURL + `/checkin/${id}`;
const postCheckoutUrl = id => baseRoomURL + `/checkout/${id}`;
const postSendOrdersTooRoomUrl = id => baseRoomURL + `/orders/${id}`;
const postRegisterOccupantsUrl = id => baseRoomURL + `/occupants/${id}`;

export const getAllRooms = async () => get(getAllRomsUrl);
export const getFreeRooms = async (params) => get(getFreeRoomsUrl, params);
export const doReservation = async (body) => post(postReservationUrl, body);
export const doCheckin = async (body, id) => post(postCheckingUrl(id), body);
export const doCheckout = async (body, id) => post(postCheckoutUrl(id), body);
export const sendOrderToRoom = async (body, id) => post(postSendOrdersTooRoomUrl(id), body);
export const registerOccupants = async (body, id) => post(postRegisterOccupantsUrl(id), body);

