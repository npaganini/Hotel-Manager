import React from 'react'
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';
import {useTranslation, withTranslation} from 'react-i18next';


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
  }
  
  TabPanel.propTypes = {
    children: PropTypes.node,
    index: PropTypes.any.isRequired,
    value: PropTypes.any.isRequired,
  };
  
  function a11yProps(index) {
    return {
      id: `scrollable-auto-tab-${index}`,
      'aria-controls': `scrollable-auto-tabpanel-${index}`,
    };
  }
  
  const useStyles = makeStyles((theme) => ({
    root: {
      flexGrow: 1,
      width: '100%',
      backgroundColor: theme.palette.background.paper,
      height: '45px',
      padding: '0px'
    },
    rightAlign: {
       marginLeft: 'auto',
    },
    tabPanel: {
      padding: '0px'
    }
  }));
  
const Navbar = (props) => {
    const classes = useStyles();
    const [value, setValue] = React.useState(0);
    const {t} = useTranslation();
  
    const handleChange = (event, newValue) => {
      setValue(newValue);
    };
  
    return (
      <div className={classes.root}>
        <AppBar position="static" color="default" >
          <Tabs 
            value={value}
            onChange={handleChange}
            indicatorColor="primary"
            textColor="primary"
            variant="scrollable"
            scrollButtons="auto"
            aria-label="scrollable auto tabs example"
          >
            <Tab label={t('registration')} {...a11yProps(0)} />
            <Tab label={t('reservation.checkin.message')} {...a11yProps(1)} />
            <Tab label={t('reservation.checkout.message')} {...a11yProps(2)} />
            <Tab label={t('reservation.plural')} {...a11yProps(3)} />
            <Tab label={t('product.plural')} {...a11yProps(4)} />
            <Tab label={t('reservation.order.plural')} {...a11yProps(5)} />
            <Tab label={t('help.request.singular')} {...a11yProps(6)} />
            <Tab label={t('user.account')} {...a11yProps(7)} className={classes.rightAlign}/>
          </Tabs>
        </AppBar>
        <TabPanel value={value} className={classes.tabPanel} index={0}>
        </TabPanel>
        <TabPanel value={value} className={classes.tabPanel} index={1}>
        </TabPanel>
        <TabPanel value={value} className={classes.tabPanel} index={2}>
        </TabPanel>
        <TabPanel value={value} className={classes.tabPanel} index={3}>
        </TabPanel>
        <TabPanel value={value} className={classes.tabPanel} index={4}>
        </TabPanel>
        <TabPanel value={value} className={classes.tabPanel} index={5}>
        </TabPanel>
        <TabPanel value={value} className={classes.tabPanel} index={6}>
        </TabPanel>
      </div>
    );
  }

  export default Navbar;
