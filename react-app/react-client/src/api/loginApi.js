import { post } from "./baseApi";

export const login = async (user, password) =>
  post("/login", { user, password });
