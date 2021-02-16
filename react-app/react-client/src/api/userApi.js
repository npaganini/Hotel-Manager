import { get, post } from "./baseApi";

const baseUserUrl = "/user";

const boughtProductsUrl = (reservationId) => baseUserUrl + `/${reservationId}/expenses`;
const getAllProductsUrl = (reservationId) => baseUserUrl + `/${reservationId}/products`;
const buyProductUrl = (reservationId) => baseUserUrl + `/${reservationId}/products`;
const helpRequestUrl = (reservationId) => baseUserUrl + `/${reservationId}/help`;
const rateStayUrl = (reservationHash) => baseUserUrl + `/ratings/${reservationHash}/rate`;

export const getBoughtProducts = (reservationId) =>
  get(boughtProductsUrl(reservationId));
export const getAllProducts = (reservationId) =>
  get(getAllProductsUrl(reservationId));
export const buyProduct = (reservationId, body) =>
  post(buyProductUrl(reservationId), body);
export const requestHelp = (reservationId, body) =>
  post(helpRequestUrl(reservationId), body);
export const rateStay = (reservationId, body) =>
  post(rateStayUrl(reservationId), body);
export const getAllReservations = () => get(baseUserUrl);
