import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

const ComposedTextField = (props) => {

  const { onChange, error, required, helperText} = props;

  const [name, setName] = React.useState();
  const classes = useStyles();


  return (
    <form className={classes.root} noValidate autoComplete="off">
        <TextField id="component-simple" label={props.label} value={name} type = {props.type} onChange={onChange} 
        fullWidth={true} error={error} required={required} helperText={helperText}/>
    </form>
  );
}

export default ComposedTextField;