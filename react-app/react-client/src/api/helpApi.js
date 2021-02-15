import { get, put } from "./baseApi";

const baseRoomURL = "/help";

const getAllHelpRequestsUrl = baseRoomURL + "";
const aHelpRequestUrl = id => baseRoomURL + `/${id}`;

export const getAllHelpRequests = async (params) => get(getAllHelpRequestsUrl, params);
export const getAHelpRequest = async (id) => get(aHelpRequestUrl(id));
export const updateHelpStep = async (id, params, body) => put(aHelpRequestUrl(id), params, body);
