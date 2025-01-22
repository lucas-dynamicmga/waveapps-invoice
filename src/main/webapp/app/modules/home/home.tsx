import './home.scss';

import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Translate } from 'react-jhipster';
import { Alert, Col, Row } from 'reactstrap';

import { REDIRECT_URL, getLoginUrl } from 'app/shared/util/url-utils';
import { useAppSelector } from 'app/config/store';
import axios from 'axios';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

export const Home = () => {
  const account = useAppSelector(state => state.authentication.account);
  const pageLocation = useLocation();
  const navigate = useNavigate();
  const [responseMessage, setResponseMessage] = useState('');

  useEffect(() => {
    const redirectURL = localStorage.getItem(REDIRECT_URL);
    if (redirectURL) {
      localStorage.removeItem(REDIRECT_URL);
      location.href = `${location.origin}${redirectURL}`;
    }
  });

  const handleCreateInvoice = async () => {
    try {
      const response = await axios.post('/api/invoices', {
        items: [
          {
            productId: 'QnVzaW5lc3M6MDE3NzQ3YTctM2E4Zi00OWU0LTg3MTMtMjVjMjcyOTQ5MjBlO1Byb2R1Y3Q6MTA2MzEyMzE5',
            quantity: 1,
            unitPrice: '100.00',
            currency: 'USD',
          },
        ],
      });
      setResponseMessage(`Response: ${JSON.stringify(response.data, null, 2)}`);
    } catch (error) {
      console.error(error);
      alert('Error creating invoice: ' + JSON.stringify(error.response?.data || error.message));
    }
  };

  return (
    <Row>
      <Col md="3" className="pad">
        <span className="hipster rounded" />
      </Col>
      <Col md="9">
        <h1 className="display-4">
          <Translate contentKey="home.title">Welcome, Java Hipster!</Translate>
        </h1>
        <p className="lead">
          <Translate contentKey="home.subtitle">This is your homepage</Translate>
        </p>
        {account?.login ? (
          <div>
            <Alert color="success">
              <Translate contentKey="home.logged.message" interpolate={{ username: account.login }}>
                You are logged in as user {account.login}.
              </Translate>
            </Alert>
          </div>
        ) : (
          <div>
            <Alert color="warning">
              <Translate contentKey="global.messages.info.authenticated.prefix">If you want to </Translate>

              <a
                className="alert-link"
                onClick={() =>
                  navigate(getLoginUrl(), {
                    state: { from: pageLocation },
                  })
                }
              >
                <Translate contentKey="global.messages.info.authenticated.link">sign in</Translate>
              </a>
              <Translate contentKey="global.messages.info.authenticated.suffix">
                , you can try the default accounts:
                <br />- Administrator (login=&quot;admin&quot; and password=&quot;admin&quot;)
                <br />- User (login=&quot;user&quot; and password=&quot;user&quot;).
              </Translate>
            </Alert>
          </div>
        )}
        <p>
          <Translate contentKey="home.question">If you have any question on JHipster:</Translate>
        </p>

        <ul>
          <li>
            <a href="https://www.jhipster.tech/" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.homepage">JHipster homepage</Translate>
            </a>
          </li>
          <li>
            <a href="https://stackoverflow.com/tags/jhipster/info" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.stackoverflow">JHipster on Stack Overflow</Translate>
            </a>
          </li>
          <li>
            <a href="https://github.com/jhipster/generator-jhipster/issues?state=open" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.bugtracker">JHipster bug tracker</Translate>
            </a>
          </li>
          <li>
            <a href="https://gitter.im/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.chat">JHipster public chat room</Translate>
            </a>
          </li>
          <li>
            <a href="https://twitter.com/jhipster" target="_blank" rel="noopener noreferrer">
              <Translate contentKey="home.link.follow">follow @jhipster on Twitter</Translate>
            </a>
          </li>
        </ul>

        <p>
          <Translate contentKey="home.like">If you like JHipster, do not forget to give us a star on</Translate>{' '}
          <a href="https://github.com/jhipster/generator-jhipster" target="_blank" rel="noopener noreferrer">
            GitHub
          </a>
          !
        </p>
      </Col>
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          justifyContent: 'center',
          height: '300px',
          backgroundColor: '#f4f6f8',
          padding: '20px',
        }}
      >
        <h2 style={{ marginBottom: '20px', color: '#333' }}>Create Invoice</h2>

        <div
          style={{
            display: 'flex',
            flexDirection: 'column',
            gap: '20px',
            alignItems: 'center',
            width: '100%',
            maxWidth: '400px',
            padding: '20px',
            backgroundColor: '#fff',
            borderRadius: '10px',
            boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
          }}
        >
          <TextField
            type="text"
            value={100}
            variant="outlined"
            label="Amount"
            sx={{
              width: '100%',
            }}
            InputProps={{
              style: { textAlign: 'center' },
            }}
          />

          <Button
            variant="contained"
            color="primary"
            onClick={handleCreateInvoice}
            sx={{
              width: '100%',
              padding: '10px 20px',
            }}
          >
            Generate Invoice
          </Button>
        </div>

        {responseMessage && (
          <div
            style={{
              marginTop: '20px',
              padding: '15px',
              border: '1px solid #e0e0e0',
              borderRadius: '10px',
              backgroundColor: '#e8f5e9',
              color: '#2e7d32',
              maxWidth: '400px',
              width: '100%',
              textAlign: 'center',
              fontSize: '14px',
            }}
          >
            {responseMessage}
          </div>
        )}
      </div>
    </Row>
  );
};

export default Home;
