import axios from "axios";

const options = (token) => ({
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
    Authorization: token,
  },
});

// FIXME add to configs
const baseURL = "http://localhost:8080";

export const post = async (url, body) =>
  axios.post(baseURL + url, body, options(window.token));

export const get = async (url, params) =>
  axios.get(baseURL + url, params, options(window.token));
