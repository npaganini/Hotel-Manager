import axios from "axios";
import cors from "cors";

const headers = {
  "Content-Type": "application/json",
  "Access-Control-Allow-Origin": "*",
  "Access-Control-Allow-Headers":
    "Origin, X-Requested-With, Content-Type, Accept",
};

export const login = async (user, password) => {
  console.log("about to do a post with", user, password);
  return axios
    .post("localhost:8080/login", { user, password }, { headers })
    .catch((error) => {
      console.log("error de axios", error);
      throw error;
    });
};
