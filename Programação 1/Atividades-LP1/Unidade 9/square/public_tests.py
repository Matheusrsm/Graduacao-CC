import unittest
import sys

undertest = __import__(sys.argv[-1].split(".py")[0])
square_code = getattr(undertest, 'square_code', None)

class PublicTests(unittest.TestCase):

    def test_1(self):
        m = "O tatu virou uma bola!!!"
        assert square_code(m) == "Ovul tima ara tob uuo"
        
if __name__ == '__main__':
    loader = unittest.TestLoader()
    runner = unittest.TextTestRunner()
    runner.run(loader.loadTestsFromModule(sys.modules[__name__]))
