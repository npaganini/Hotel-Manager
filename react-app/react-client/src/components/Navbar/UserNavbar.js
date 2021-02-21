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

const Navbar = ({ history, setIsClient, setIsLoggedIn }) => {
  const classes = useStyles();
  const [value, setValue] = React.useState(0);
  const { t } = useTranslation();

  const [showDropdown, setShowDropdown] = React.useState(undefined);
  const inputEl = useRef(null);

  const homeOnClick = () => {
    history.push("/");
  };

  const handleClose = () => setShowDropdown(undefined);

  const handleChange = (_, newValue) => {
    if (newValue === 1) {
      setShowDropdown(true);
    } else {
      setValue(newValue);
    }
  };

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    window.alert("Logout successful");
    setIsLoggedIn(false);
    setIsClient(false);
    history.push("/login");
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
