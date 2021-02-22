import React, { useRef } from "react";
import PropTypes from "prop-types";
import { makeStyles } from "@material-ui/core/styles";
import AppBar from "@material-ui/core/AppBar";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import { useTranslation, withTranslation } from "react-i18next";
import { withRouter } from "react-router";
import Menu from "@material-ui/core/Menu";
import MenuItem from "@material-ui/core/MenuItem";

const TabPanel = (props) => {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`scrollable-auto-tabpanel-${index}`}
      aria-labelledby={`scrollable-auto-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box p={3}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
};

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired,
};

function a11yProps(index) {
  return {
    id: `scrollable-auto-tab-${index}`,
    "aria-controls": `scrollable-auto-tabpanel-${index}`,
  };
}

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
    width: "100%",
    backgroundColor: theme.palette.background.paper,
    height: "45px",
    padding: "0px",
  },
  rightAlign: {
    marginLeft: "auto",
  },
  tabPanel: {
    padding: "0px",
  },
  menuLogout: {
    marginTop: "30px",
  },
}));

// TODO currentPath to value
const Navbar = ({ history, setIsLoggedIn, setIsClient }) => {
  const classes = useStyles();

  const { location } = history;
  const { pathname } = location;

  const [value, setValue] = React.useState(0);
  const [showDropdown, setShowDropdown] = React.useState(undefined);
  const inputEl = useRef(null);

  const { t } = useTranslation();

  const handleChange = (_, newValue) => {
    if (newValue === 9) {
      setShowDropdown(true);
    } else {
      setValue(newValue);
    }
  };

  const handleClose = () => setShowDropdown(undefined);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    setIsLoggedIn(false);
    setIsClient(false);
    history.push("/login");
  };

  const homeOnClick = () => {
    history.push("/");
  };

  const registrationOnClick = () => {
    history.push("/registration");
  };

  const checkInOnClick = () => {
    history.push("/checkin");
  };

  const checkOutOnClick = () => {
    history.push("/checkout");
  };

  const reservationsOnClick = () => {
    history.push("/reservations");
  };

  const productsOnClick = () => {
    history.push("/products");
  };

  const ordersOnClick = () => {
    history.push("/orders");
  };

  const ratesOnClick = () => {
    history.push("/rates")
  };

  const helpRequestOnClick = () => {
    history.push("/help");
  };

  return (
    <div className={classes.root}>
      <AppBar position="static" color="default">
        <Tabs
          value={value}
          onChange={handleChange}
          indicatorColor="primary"
          textColor="primary"
          variant="scrollable"
          scrollButtons="auto"
          aria-label="scrollable auto tabs example"
        >
          <Tab label={t("Home")} onClick={homeOnClick} {...a11yProps(8)} />
          <Tab
            label={t("registration")}
            onClick={registrationOnClick}
            {...a11yProps(0)}
          />
          <Tab
            label={t("reservation.checkin.message")}
            onClick={checkInOnClick}
            {...a11yProps(1)}
          />
          <Tab
            label={t("reservation.checkout.message")}
            onClick={checkOutOnClick}
            {...a11yProps(2)}
          />
          <Tab
            label={t("reservation.plural")}
            onClick={reservationsOnClick}
            {...a11yProps(3)}
          />
          <Tab
            label={t("product.plural")}
            onClick={productsOnClick}
            {...a11yProps(4)}
          />
          <Tab
            label={t("reservation.order.plural")}
            onClick={ordersOnClick}
            {...a11yProps(5)}
          />
          <Tab
            label={t("rates")}
            onClick={ratesOnClick}
            {...a11yProps(8)}
          />
          <Tab
            label={t("help.request.singular")}
            onClick={helpRequestOnClick}
            {...a11yProps(6)}
          />
          <Tab
            label={t("user.account")}
            {...a11yProps(7)}
            className={classes.rightAlign}
            ref={inputEl}
          />
          <Menu
            className={classes.menuLogout}
            anchorOrigin={{
              vertical: "bottom",
              horizontal: "center",
            }}
            transformOrigin={{ vertical: "bottom", horizontal: "center" }}
            anchorEl={inputEl.current}
            keepMounted
            open={Boolean(showDropdown)}
            onClose={handleClose}
          >
            <MenuItem onClick={handleLogout}>Logout</MenuItem>
          </Menu>
        </Tabs>
      </AppBar>
    </div>
  );
};

export default withRouter(Navbar);
