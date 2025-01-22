import { makeStyles } from '@mui/styles';
import { createTheme } from '@mui/material/styles';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';

const useStyles = makeStyles(() => ({
  container: {
    backgroundColor: '#f0f0f0',
    padding: '64px 0',
    textAlign: 'center',
  },
  title: {
    fontSize: '2rem',
    color: '#333',
  },
  cardGrid: {
    padding: '20px 0',
  },
}));

export const appTheme = createTheme({
  palette: {
    background: {
      default: '#fafafa',
    },
    text: {
      primary: '#333',
      secondary: '#666',
    },
  },
});

export const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.background.default,
  ...theme.typography.body2,
  padding: theme.spacing(1),
  textAlign: 'center',
  color: theme.palette.text.secondary,
}));

export default useStyles;
