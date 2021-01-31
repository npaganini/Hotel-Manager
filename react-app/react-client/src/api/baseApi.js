import axios from "axios";

const options = {
  headers: {
    "Content-Type": "application/json;charset=UTF-8",
  },
};

const getOptions = (url) => {
  if (url == "/login") return options;
  options.headers.Authorization = "Bearer " + window.token;
  console.log(options);
  return options;
};

// FIXME add to configs
const baseURL = "http://localhost:8080";

export const post = async (url, body) =>
  axios.post(baseURL + url, body, getOptions(url));

export const get = async (url, params) =>
  axios.get(baseURL + url, params, getOptions(url));
