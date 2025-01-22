import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Col, Row } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './quote.reducer';

export const QuoteDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const quoteEntity = useAppSelector(state => state.newapp.quote.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="quoteDetailsHeading">
          <Translate contentKey="newApp.quote.detail.title">Quote</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.id}</dd>
          <dt>
            <span id="identifier">
              <Translate contentKey="newApp.quote.identifier">Identifier</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.identifier}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="newApp.quote.description">Description</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.description}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="newApp.quote.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.createdBy}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="newApp.quote.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.createdDate ? <TextFormat value={quoteEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="lastModifiedBy">
              <Translate contentKey="newApp.quote.lastModifiedBy">Last Modified By</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.lastModifiedBy}</dd>
          <dt>
            <span id="lastModifiedDate">
              <Translate contentKey="newApp.quote.lastModifiedDate">Last Modified Date</Translate>
            </span>
          </dt>
          <dd>
            {quoteEntity.lastModifiedDate ? <TextFormat value={quoteEntity.lastModifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="status">
              <Translate contentKey="newApp.quote.status">Status</Translate>
            </span>
          </dt>
          <dd>{quoteEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/quote" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/quote/${quoteEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default QuoteDetail;
