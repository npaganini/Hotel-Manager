import { post, get } from "./baseApi";

const baseProductsURL = "/products";

const addProductFileUrl = baseProductsURL + "/upload-file";
const getProductFileUrl = (id) => baseProductsURL + "/" + id + "/img";
const addProductUrl = baseProductsURL;
const enableProductUrl = (id) => baseProductsURL + "/" + id + "/enable";
const disableProductUrl = (id) => baseProductsURL + "/" + id + "/disable";
const findAllProductsUrl = baseProductsURL + "/all";

export const uploadProductFile = async (body) => post(addProductFileUrl, body);
export const addProduct = async (body) => post(addProductUrl, body);
export const getProductFile = async (id) => get(getProductFileUrl(id));
export const enableProduct = async (id) => post(enableProductUrl(id));
export const disableProduct = async (id) => post(disableProductUrl(id));
export const getAllProducts = async () => get(findAllProductsUrl);
