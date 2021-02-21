import { get } from "./baseApi";

const baseRatingsUrl = "/ratings";

const getAvgHotelRatingUrl = baseRatingsUrl;
const getAllHotelRatingsUrl = baseRatingsUrl + "/hotel";
const getAvgRoomRatingUrl = (roomNumber) =>
  baseRatingsUrl + `/rooms/${roomNumber}`;
const getRoomRatingsUrl = (roomNumber) =>
  getAvgRoomRatingUrl(roomNumber) + "/all";

export const getAvgHotelRating = async () => get(baseRatingsUrl);
export const getAllHotelRatings = async (params) => get(getAllHotelRatingsUrl, params);
export const getAvgRoomRating = (roomNumber) =>
  get(getAvgRoomRatingUrl(roomNumber));
export const getAllRoomRatings = (roomNumber) =>
  get(getRoomRatingsUrl(roomNumber));
