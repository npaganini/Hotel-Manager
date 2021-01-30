import { post, get } from "./baseApi";

const baseRoomURL = "/rooms";

const getAllRomsUrl = baseRoomURL + "";
const getFreeRoomsUrl = baseRoomURL + "/free";
const postReservationUrl = baseRoomURL + "/reservation";
const postCheckingUrl = baseRoomURL + "/checkin";
const postCheckoutUrl = baseRoomURL + "/checkout";
const postSendOrdersTooRoomUrl = baseRoomURL + "/orders";
const postRegisterOccupantsUrl = baseRoomURL + "/occupants";

const getAllRooms = async () => get(getAllRomsUrl);
const getFreeRooms = async (params) => get(getFreeRoomsUrl, params);
const doReservation = async (body) => post(postReservationUrl, body);
const doCheckin = async (body) => post(postCheckingUrl, body);
const doCheckout = async (body) => post(postCheckoutUrl, body);
const sendOrderToRoom = async (body) => post(postSendOrdersTooRoomUrl, body);
const registerOccupants = async (body) => post(postRegisterOccupantsUrl, body);

export const {
  getAllRooms,
  getFreeRooms,
  doReservation,
  doCheckin,
  doCheckout,
  sendOrderToRoom,
  registerOccupants,
};
