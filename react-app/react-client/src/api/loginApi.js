import { post } from "./baseApi";

const CLIENT_ROLE = "CLIENT";
const MANAGER_ROLE = "MANAGER";

export const login = async (username, password) =>
  post("/login", { username, password }).then((result) => {
    localStorage.setItem("token", result.data.token);
    localStorage.setItem("role", result.data.role);
    return result;
  });
