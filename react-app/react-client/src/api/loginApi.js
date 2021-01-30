import axios from "axios";

const options = {
  headers: {
    "Content-Type": "application/json;charset=UTF-8"
  },
};

export const login = async (user, password) => {
  console.log("about to do a post with", user, password);
  return axios
    .post("http://localhost:8080/login", { user, password }, options)
    .catch((error) => {
      console.log("error de axios", error);
      throw error;
    });
};
