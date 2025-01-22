import { AppBar, Container, CssBaseline, Toolbar, Typography } from '@mui/material';
import PhotoCamera from '@mui/icons-material/PhotoCamera';
import React from 'react';
import { ThemeProvider } from '@mui/material/styles';
import useStyles, { appTheme, Item } from 'app/shared/util/classes';
import Grid from '@mui/material/Grid2';
import { Button } from 'reactstrap';

const MaterialUITest: React.FC = () => {
  const classes = useStyles();

  return (
    <ThemeProvider theme={appTheme}>
      <CssBaseline />
      <AppBar position="relative">
        <Toolbar>
          <PhotoCamera />
          <Typography variant="h6">Photo Album</Typography>
        </Toolbar>
      </AppBar>
      <main>
        <div className={classes.container}>
          <Container maxWidth="sm">
            <Typography className={classes.title} gutterBottom>
              Photo Album
            </Typography>
            <Typography variant="h5" color="textSecondary">
              Hello everyone! This is a photo album, and I am trying to make this a long sentence so we can see how it looks on the screen.
            </Typography>
          </Container>
        </div>
        <Container className={classes.cardGrid} maxWidth="md">
          <Grid container spacing={2}>
            <Grid size={8}>
              <Item>
                <Typography variant="h5" color="textSecondary">
                  This is a photo album
                </Typography>
                <Typography variant="h5" color="textSecondary">
                  Media
                </Typography>
                <Button contained style={{ marginRight: '10px' }}>
                  View
                </Button>
                <Button outline>Buy</Button>
              </Item>
            </Grid>
            <Grid size={4}>
              <Item>
                <Typography variant="h5" color="textSecondary">
                  This is a photo album
                </Typography>
                <Typography variant="h5" color="textSecondary">
                  Media
                </Typography>
                <Button contained style={{ marginRight: '10px' }}>
                  View
                </Button>
                <Button outline>Buy</Button>
              </Item>
            </Grid>
            <Grid size={4}>
              <Item>
                <Typography variant="h5" color="textSecondary">
                  This is a photo album
                </Typography>
                <Typography variant="h5" color="textSecondary">
                  Media
                </Typography>
                <Button contained style={{ marginRight: '10px' }}>
                  View
                </Button>
                <Button outline>Buy</Button>
              </Item>
            </Grid>
            <Grid size={8}>
              <Item>
                <Typography variant="h5" color="textSecondary">
                  This is a photo album
                </Typography>
                <Typography variant="h5" color="textSecondary">
                  Media
                </Typography>
                <Button contained style={{ marginRight: '10px' }}>
                  View
                </Button>
                <Button outline>Buy</Button>
              </Item>
            </Grid>
          </Grid>
        </Container>
      </main>
    </ThemeProvider>
  );
};

export default MaterialUITest;
