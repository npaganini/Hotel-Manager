import React from "react";
import { Route, Redirect } from "react-router-dom";
import { managerPaths, clientPaths, CLIENT, MANAGER } from "./routesByRole";

export const PrivateRoute = ({ component: Component, ...routeProps }) => (
  <Route
    render={(props) => {
      const currentUser = localStorage.getItem("token");
      if (!currentUser) {
        // not logged in so redirect to login page with the return url
        return <Redirect to={{ pathname: "/login" }} />;
      }

      const role = localStorage.getItem("role");
      const path = routeProps;

      if (clientPaths.indexOf(path) == -1 && managerPaths.indexOf(path) == -1) {
        // TODO show some not found page
      }

      if (!role) {
        // TODO show some forbidden page
      }

      if (role === CLIENT) {
        if (clientPaths.indexOf(path) == -1) {
          // TODO show some forbidden page
        }
      } else if (role === MANAGER) {
        if (managerPaths.indexOf(path) == -1) {
          // TODO show some forbidden page
        }
      }

      // authorised so return component
      return <Component {...props} />;
    }}
    {...routeProps}
  />
);
