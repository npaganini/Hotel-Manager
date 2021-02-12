import { get, post } from "./baseApi";

const baseUserUrl = "/user";

const boughtProductsUrl = (reservationId) => `/expenses/${reservationId}`;
const getAllProductsUrl = (reservationId) => `/${reservationId}/products`;
const buyProductUrl = (reservationId) => `/${reservationId}/products`;
const helpRequestUrl = (reservationId) => `/${reservationId}/help`;
const rateStayUrl = (reservationHash) => `/ratings/${reservationHash}/rate`;

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
