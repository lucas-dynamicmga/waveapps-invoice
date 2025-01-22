import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './invoice.reducer';

export const InvoiceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const invoiceEntity = useAppSelector(state => state.newapp.invoice.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="invoiceDetailsHeading">
          <Translate contentKey="newApp.invoice.detail.title">Invoice</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.id}</dd>
          <dt>
            <span id="invoiceId">
              <Translate contentKey="newApp.invoice.invoiceId">Invoice Id</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.invoiceId}</dd>
          <dt>
            <span id="agencyId">
              <Translate contentKey="newApp.invoice.agencyId">Agency Id</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.agencyId}</dd>
          <dt>
            <span id="statusId">
              <Translate contentKey="newApp.invoice.statusId">Status Id</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.statusId}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="newApp.invoice.title">Title</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.title}</dd>
          <dt>
            <span id="subhead">
              <Translate contentKey="newApp.invoice.subhead">Subhead</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.subhead}</dd>
          <dt>
            <span id="invoiceNumber">
              <Translate contentKey="newApp.invoice.invoiceNumber">Invoice Number</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.invoiceNumber}</dd>
          <dt>
            <span id="poNumber">
              <Translate contentKey="newApp.invoice.poNumber">Po Number</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.poNumber}</dd>
          <dt>
            <span id="dueData">
              <Translate contentKey="newApp.invoice.dueData">Due Data</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.dueData ? <TextFormat value={invoiceEntity.dueData} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="pdfUrl">
              <Translate contentKey="newApp.invoice.pdfUrl">Pdf Url</Translate>
            </span>
          </dt>
          <dd>{invoiceEntity.pdfUrl}</dd>
        </dl>
        <Button tag={Link} to="/invoice" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/invoice/${invoiceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default InvoiceDetail;
