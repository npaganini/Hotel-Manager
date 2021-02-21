import React from "react";
import { Route, Redirect } from "react-router-dom";
import { managerPaths, clientPaths, CLIENT, MANAGER } from "./routesByRole";
import Forbidden from "../../containers/Forbidden/Forbidden";

export const PrivateRoute = ({
  component: Component,
  setIsLoggedIn,
  setIsClient,
  ...routeProps
}) => (
  <Route
    render={(props) => {
      const currentUser = localStorage.getItem("token");
      const role = localStorage.getItem("role");

      console.log("route props", routeProps);
      console.log("props", props);

      const { path } = routeProps;

      if (path === "/login") {
        if (role && currentUser) {
          return <Redirect to={{ pathname: "/" }} />;
        }
        return (
          <Component
            {...Object.assign({}, ...props, { setIsClient, setIsLoggedIn })}
          />
        );
      }

      if (!currentUser || !role) {
        // not logged in so redirect to login page with the return url
        return <Redirect to={{ pathname: "/login" }} />;
      }

      if (role === CLIENT) {
        if (clientPaths.indexOf(path) == -1) {
          return <Forbidden />;
        }
      } else if (role === MANAGER) {
        if (managerPaths.indexOf(path) == -1) {
          return <Forbidden />;
        }
      }

      // authorised so return component
      return <Component {...props} />;
    }}
    {...routeProps}
  />
);
