import { post, get } from "./baseApi";

const baseRoomURL = "/rooms";

const getAllRomsUrl = baseRoomURL + "";
const getFreeRoomsUrl = baseRoomURL + "/free";
const postReservationUrl = baseRoomURL + "/reservation";
const postCheckingUrl = baseRoomURL + "/checkin";
const postCheckoutUrl = baseRoomURL + "/checkout";
const postSendOrdersTooRoomUrl = baseRoomURL + "/orders";
const postRegisterOccupantsUrl = baseRoomURL + "/occupants";

export const getAllRooms = async () => get(getAllRomsUrl);
export const getFreeRooms = async (params) => get(getFreeRoomsUrl, params);
export const doReservation = async (body) => post(postReservationUrl, body);
export const doCheckin = async (body) => post(postCheckingUrl, body);
export const doCheckout = async (body) => post(postCheckoutUrl, body);
export const sendOrderToRoom = async (body) => post(postSendOrdersTooRoomUrl, body);
export const registerOccupants = async (body) => post(postRegisterOccupantsUrl, body);

