import { post } from "./baseApi";

export const login = async (username, password) =>
  post("/login", { username, password });
