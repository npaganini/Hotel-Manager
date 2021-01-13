import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles((theme) => ({
  textField: {
    marginLeft: theme.spacing(1),
    marginRight: theme.spacing(1),
    display: 'flex',
    flexWrap: 'wrap',
  },
}));

const DatePickers = (props) => {
  const classes = useStyles();

  console.log(props.Date);
  return (      
      <TextField
        id={props.Id}
        label={props.label}
        type="date"
        className={classes.textField}
        InputLabelProps={{
          shrink: true,
        }}
      />
  );
}

export default DatePickers;