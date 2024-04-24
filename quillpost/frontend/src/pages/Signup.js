import {
  Card,
  CardBody,
  CardHeader,
  Container,
  Input,
  Label,
  Form,
  FormGroup,
  Button,
  Row,
  Col,
  FormFeedback,
} from 'reactstrap';
import Base from '../components/Base';
import { useEffect, useState } from 'react';
import { SignUp } from '../services/user-service';
import { toast } from 'react-toastify';

const Signup = () => {
  //----------new-------------
  //create hook

  const [data, setData] = useState({
    username: '',
    first_name: '',
    last_name: '',
    email: '',
    password: '',
  });

  const [error, setError] = useState({
    errors: {},
    isError: false,
  });

  useEffect(() => {
    console.log(data);
  }, [data]);

  //handle change
  const handleChange = (event, property) => {
    //dynamic setting the values
    setData({ ...data, [property]: event.target.value });
  };

  //reseting the form
  const resetData = () => {
    setData({
      username: '',
      first_name: '',
      last_name: '',
      email: '',
      password: '',
    });
  };

  //submit the form
  const submitForm = (event) => {
    event.preventDefault();

    //if(error.isError) {
    //    toast.error("Form data is invalid!!")
    //    return;
    //}

    console.log(data);
    //data validation

    //call server api for sending the data
    SignUp(data)
      .then((resp) => {
        console.log(resp);
        console.log('Success log');
        toast.success('Regestration Successfully!!');
        setData({
          username: '',
          first_name: '',
          last_name: '',
          email: '',
          password: '',
        });
      })
      .catch((error) => {
        console.log(error);
        console.log('Error log');

        //handle Error
        setError({
          errors: error,
          isError: true,
        });
      });
  };

  //----------end-------------
  return (
    <Base>
      <Container>
        <Row className='mt-4'>
          <Col sm={{ size: 6, offset: 3 }}>
            <Card color='dark' inverse>
              <CardHeader className='text-center'>
                <h3>SignUp</h3>
              </CardHeader>
              <CardBody>
                {/* Create Form */}

                <Form onSubmit={submitForm}>
                  {/*User Field */}
                  <FormGroup>
                    <Label for='username'>User Name</Label>
                    <Input
                      type='text'
                      placeholder='Choose a unique username'
                      id='username'
                      required
                      onChange={(e) => handleChange(e, 'username')}
                      value={data.username}
                      invalid={
                        error.errors?.response?.data?.username ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.username}
                    </FormFeedback>
                  </FormGroup>

                  {/*First Name Filed*/}
                  <FormGroup>
                    <Label for='firstname'>Enter First Name</Label>
                    <Input
                      type='text'
                      placeholder='Enter Your First Name'
                      id='firstname'
                      required
                      onChange={(e) => handleChange(e, 'first_name')}
                      value={data.first_name}
                      invalid={
                        error.errors?.response?.data?.first_name ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.first_name}
                    </FormFeedback>
                  </FormGroup>

                  {/*Last Name Filed*/}
                  <FormGroup>
                    <Label for='lastname'>Enter Last Name</Label>
                    <Input
                      type='text'
                      placeholder='Enter Your Last Name'
                      id='lastname'
                      required
                      onChange={(e) => handleChange(e, 'last_name')}
                      value={data.last_name}
                      invalid={
                        error.errors?.response?.data?.last_name ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.last_name}
                    </FormFeedback>
                  </FormGroup>

                  {/*email Filed*/}
                  <FormGroup>
                    <Label for='email'>Enter email</Label>
                    <Input
                      type='email'
                      placeholder='name@example.com'
                      id='email'
                      required
                      onChange={(e) => handleChange(e, 'email')}
                      value={data.email}
                      invalid={
                        error.errors?.response?.data?.email ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.email}
                    </FormFeedback>
                  </FormGroup>

                  {/*password Filed*/}
                  <FormGroup>
                    <Label for='password'>Choose a Password</Label>
                    <Input
                      type='password'
                      placeholder='Choose Your password'
                      id='password'
                      required
                      onChange={(e) => handleChange(e, 'password')}
                      value={data.password}
                      invalid={
                        error.errors?.response?.data?.password ? true : false
                      }
                    />
                    <FormFeedback>
                      {error.errors?.response?.data?.password}
                    </FormFeedback>
                  </FormGroup>

                  {/*
                                    <FormGroup>
                                        <Label for="password">Confirm Password</Label>
                                        <Input type="password" placeholder='Enter your password again' id="password" required></Input>
                                    </FormGroup>
    */}

                  <Container className='text-center'>
                    <Button color='primary' className='mt-4'>
                      Register
                    </Button>
                    <Button
                      onClick={resetData}
                      color='secondary'
                      type='reset'
                      className='ms-3 mt-4'
                    >
                      Reset
                    </Button>
                  </Container>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  );
};

export default Signup;
