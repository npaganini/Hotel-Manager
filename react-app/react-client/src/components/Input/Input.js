import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormControl from '@material-ui/core/FormControl';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
    },
  },
}));

const ComposedTextField = (props) => {
  const [name, setName] = React.useState();
  const classes = useStyles();

  const handleChange = (event) => {
    setName(event.target.value);
  };

  return (
    <form className={classes.root} noValidate autoComplete="off">
      <FormControl>
        <InputLabel htmlFor="component-simple">{props.label}</InputLabel>
        <Input id="component-simple" value={name} type = {props.type} onChange={handleChange} />
      </FormControl>
    </form>
  );
}

export default ComposedTextField;