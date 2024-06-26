import {
  Card,
  Col,
  Container,
  Row,
  CardHeader,
  CardBody,
  FormGroup,
  Input,
  Label,
  Form,
  Button,
} from 'reactstrap';
import Base from '../components/Base';
import { useState } from 'react';
import { toast } from 'react-toastify';
import { loginUser } from '../services/user-service';
import { doLoggin } from '../auth';
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const navigate = useNavigate();

  const [loginDetail, setLoginDetail] = useState({
    username: '',
    password: '',
  });

  const handleChange = (event, field) => {
    let actualvalue = event.target.value;
    setLoginDetail({
      ...loginDetail,
      [field]: actualvalue,
    });
  };

  const handleReset = () => {
    setLoginDetail({
      username: '',
      password: '',
    });
  };

  const handleFormSubmit = (event) => {
    event.preventDefault();
    console.log(loginDetail);

    //validation
    if (
      loginDetail.username.trim() === '' ||
      loginDetail.password.trim() === ''
    ) {
      toast.error('Username is Required!!');
      return;
    }

    //submit the data to server to generate token
    loginUser(loginDetail)
      .then((data) => {
        console.log(data);

        //save the data to local storage
        doLoggin(data, () => {
          console.log('login details saved to local storage');

          //redirect to user dashboard page
          navigate('/user/dashboard');
        });

        toast.success('Login Success!!');
      })
      .catch((error) => {
        console.log(error);
        if (error.response.status === 400 || error.response.status === 404) {
          toast.error(error.response.data.message);
        } else {
          toast.error('Something Worng!!');
        }
      });
  };

  return (
    <Base>
      <Container>
        <Row className='mt-4'>
          <Col
            sm={{
              size: 6,
              offset: 3,
            }}
          >
            <Card color='dark' inverse>
              <CardHeader className='text-center'>
                <h3>Login</h3>
              </CardHeader>

              <CardBody>
                <Form onSubmit={handleFormSubmit}>
                  {/*username Field */}

                  <FormGroup>
                    <Label for='username'>Enter Username</Label>

                    <Input
                      type='text'
                      id='username'
                      placeholder='Enter Your User Name'
                      required
                      value={loginDetail.username}
                      onChange={(e) => handleChange(e, 'username')}
                    />
                  </FormGroup>

                  {/*password Field */}

                  <FormGroup>
                    <Label for='password'>Enter Password</Label>

                    <Input
                      type='password'
                      id='password'
                      placeholder='Enter Your Password'
                      required
                      value={loginDetail.password}
                      onChange={(e) => handleChange(e, 'password')}
                    />
                  </FormGroup>

                  {/* <Container className="text-center">
                                        <Button color='secondary' className='mt-4'>Login</Button>
                                        <Button className='ms-3 mt-4'color = "secondary">Reset</Button>
                                    </Container> */}

                  <Container className='text-center'>
                    <Button color='primary' className='mt-4'>
                      Login
                    </Button>
                    <Button
                      onClick={handleReset}
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

export default Login;
